import React, { Component } from 'react';
import SingleChoiceQuestion from './SingleChoiceQuestion'

class App extends Component {

 constructor(props) {
    super(props);

    this.state = {
      questions: [],
      answers: [],
      displayQuestions: false
    }
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
        console.log("Question " + event.target.name)
        console.log("Selected " + event.target.value)
    }

    render() {
       return(
          <form>
           <div className="App">
           <h1>Personality Test</h1>
            UserId <input type="text" name="userId" />
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
                                                      changed= {this.radioSingleChoiceChangeHandler} />
                        default: return null;
                    }

                 })}
            </div>
           }
           </div>
           <input type="submit" value="Submit" onClick= {(e) => {e.preventDefault()} } />
           </form>
       )
    }

}
export default App;


//{
//      "questionId": "100",
//      "answer_type": {
//        "type": "single_choice",
//        "answer": "male"
//      }
//    }