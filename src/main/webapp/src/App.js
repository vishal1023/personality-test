import React, { Component } from 'react';
import SingleChoiceQuestion from './SingleChoiceQuestion'

class App extends Component {

 constructor(props) {
    super(props);

    this.state = {
      userId: '',
      questions: [],
      answers: [],
      displayQuestions: false
    }

    this._addAnswerSingleChoice = this._addAnswerSingleChoice.bind(this)
 }

   componentDidMount(){
        this.getQuestions();
    }

   getQuestions(){
    fetch("/personality-test/questions")
          .then(res => res.json())
          .then(res => this.setState({questions: res}));

    console.log("Loaded successfully")
   }

    displayQuestion = (event) => {
         event.preventDefault();
        this.setState({
            displayQuestions: true
        })
    }

    radioSingleChoiceChangeHandler = (event) => {
        this.setState({
            answers: event.target.value
        })
    }

    userIdChangeHandler = (event) => {
        console.log("User Id set to " + event.target.value)
        this.setState({
            userId: event.target.value
        })
    }

    submitAns = (event) => {
        console.log("Answers")
        console.log(this.state.answers)
        console.log(this.state.userId)
        const testData = {
            "personalityTestKey":{"userId": this.state.userId,"testId": "personalityTest"},
            "answers": this.state.answers}
        console.log("Test data to send backend " + JSON.stringify(testData))

        fetch("/personality-test/answers", {
          method: "POST",
          headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
          },
          body: JSON.stringify(testData)
        }).then(function(response) {
             console.log(response)
        })

    }

    _addAnswerSingleChoice(e) {
      const doesAlreadyAnswered = this.state.answers.find(x => x.questionId === e.target.name) !== undefined;
      console.log("Is que answered - " + doesAlreadyAnswered)
      let answerCopy = this.state.answers;
      if(doesAlreadyAnswered) {
         this.state.answers.map(a => {
            if(a.questionId === e.target.name) {
                a.answer = e.target.value
            }
          })
       } else {
          const ans = {'questionId': e.target.name, 'answerType':{'type':'SingleChoiceAnswer', 'answerText': e.target.value}}
          answerCopy.push(ans);
          this.setState({
            answers: answerCopy
          });
       }
      console.log(this.state.answers);
    }

    render() {
       return(
          <form>
           <div className="App">
           <h1>Personality Test</h1>
            UserId <input type="text" name="userId" onChange = { (e) => {this.userIdChangeHandler(e)}} />
            <button className="btn" onClick={ (e) => { this.displayQuestion(e)}}>Take test</button>
           {
           this.state.displayQuestions &&
            <div>
                 { this.state.questions.questions.map((question, index) => {
                    switch (question.question_type.type) {
                        case 'single_choice':
                                return <SingleChoiceQuestion key={question.questionText}
                                                      options={question.question_type.options}
                                                      questionText={question.questionText}
                                                      changed= {this._addAnswerSingleChoice} />
                        default: return null;
                    }

                 })}
            </div>
           }
           </div>
            <input type="submit" value="Submit" onClick= {(e) => {e.preventDefault(); this.submitAns(e)} } />
           </form>
       )
    }

}
export default App;
