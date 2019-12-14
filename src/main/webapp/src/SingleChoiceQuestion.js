import React from 'react';

const SingleChoiceQuestion = ( props ) => {
  return (
    <div className="SingleChoiceQuestion">
    <p>{props.question}</p>
    <div className="radio">
        {props.options.map(function(option, index) {
            return  <td>{option}<input type="radio" value={option} name={props.question} onChange= {props.changed}/></td>
        })}
    </div>
   </div>
  )
 };

 export default SingleChoiceQuestion;