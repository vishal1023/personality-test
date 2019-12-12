import React from 'react';

const SingleChoiceQuestion = ( props ) => {
  return (
    <div className="SingleChoiceQuestion">
    <p>{props.questionText}</p>
    <div className="radio">
        {props.options.map(function(option, index) {
            return  <td>{option}<input type="radio" value={option} name={props.questionText} /></td>
        })}
    </div>
   </div>
  )
 };

 export default SingleChoiceQuestion;