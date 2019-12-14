import React, { Component } from 'react';
import SingleChoiceQuestion from './SingleChoiceQuestion'
import SingleChoiceConditionalQuestion from './SingleChoiceConditionalQuestion'
import RangeQuestion from './RangeQuestion'


class App extends Component {

 constructor(props) {
    super(props);

    this.state = {
      userId: '',
      questions: [],
      answers: [],
      displayQuestions: false,
      conditionalQuestions: [],
    }

    this._addAnswerSingleChoice = this._addAnswerSingleChoice.bind(this);
    this._addAnswerAndCheckCondition = this._addAnswerAndCheckCondition.bind(this);
    this.findArrayElementByTitle = this.findArrayElementByTitle.bind(this);
    this.rangeHandler = this.rangeHandler.bind(this);
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
                a.answerType.answerText = e.target.value
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

     rangeHandler (e) {
        const doesAlreadyAnswered = this.state.answers.find(x => x.questionId === e.target.id) !== undefined;
        console.log("To Handler Is que answered - " + doesAlreadyAnswered)
        let answerCopy = this.state.answers;
        if(doesAlreadyAnswered) {
            this.state.answers.map(a => {
                if(a.questionId === e.target.id) {
                    if(e.target.name === 'to') {
                        a.answerType.to = e.target.value
                     }else{
                        a.answerType.from = e.target.value
                     }
                }
            })
        } else {
            if(e.target.name === 'to') {
                const ans = {'questionId': e.target.id, 'answerType':{'type':'NumberRangeAnswer', 'to': e.target.value}}
                answerCopy.push(ans);
            }else{
                const ans = {'questionId': e.target.id, 'answerType':{'type':'NumberRangeAnswer', 'from': e.target.value}}
                answerCopy.push(ans);
            }
            this.setState({
                answers: answerCopy
            });
        }
        console.log(this.state.answers);
        }

    _addAnswerAndCheckCondition(e, condition) {
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
            const ans = {'questionId': e.target.name, 'answerType':{'type':'SingleChoiceConditionalAnswer', 'answerText': e.target.value}}
            answerCopy.push(ans);
            this.setState({
                answers: answerCopy
            });
        }
        console.log(this.state.answers);

       let conditionalQuestionsCopy = this.state.conditionalQuestions;

      if(e.target.value === condition.predicate.exactEquals[1]) {
        const conditionalQuestion = {'questionId': e.target.name, 'isPositive':true}
        conditionalQuestionsCopy.push(conditionalQuestion);
        this.setState({
            conditionalQuestions:conditionalQuestionsCopy
        })
      }else {
         const conditionalQuestion = {'questionId': e.target.name, 'isPositive':false}
         conditionalQuestionsCopy.push(conditionalQuestion);
         this.setState({
             conditionalQuestions:conditionalQuestionsCopy
         })
      }
    }

    findArrayElementByTitle(title) {
      return this.state.conditionalQuestions.find((element) => {
        return element.title === title && element.isPositive === true;
      })
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
                 { this.state.questions.questions.map((que, index) => {
                    switch (que.question_type.type) {
                        case 'single_choice':
                                return <SingleChoiceQuestion key={que.question}
                                                      options={que.question_type.options}
                                                      question={que.question}
                                                      changed= {this._addAnswerSingleChoice} />
                        case 'single_choice_conditional':
                                if(this.findArrayElementByTitle(this.conditionalQuestions, que.question)) {
                                    console.log("Condition passed");
                                return <div>
                                        <SingleChoiceConditionalQuestion key={que.question}
                                                     options={que.question_type.options}
                                                     question={que.question}
                                                     condition={que.question_type.condition}
                                                     changed= {(e) => this._addAnswerAndCheckCondition(e, que.question_type.condition)} />
                                         <RangeQuestion key={que.question}
                                                    question= {que.question_type.condition.if_positive.question}
                                                    defaultRange= {que.question_type.condition.if_positive}
                                                    rangeHandler = {this.rangeHandler}  />
                                       </div>
                                       }
                                       else {
                                        return <div>
                                            <SingleChoiceConditionalQuestion key={que.question}
                                                     options={que.question_type.options}
                                                     question={que.question}
                                                     condition={que.question_type.condition}
                                                     changed= {(e) => this._addAnswerAndCheckCondition(e, que.question_type.condition)} />
                                           </div>
                                       }

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
