import React from 'react';

const RangeQuestion = ( props ) => {
  return (
   <div className="RangeQuestion">
       <p>{props.question}</p>
        From <input type="text" id={props.question} defaultValue={props.defaultRange.question_type.range.from} name="from" onChange = {props.rangeHandler} />
        To <input type="text" id={props.question} defaultValue={props.defaultRange.question_type.range.to} name="to" onChange = {props.rangeHandler} />
   </div>
  )
 };

 export default RangeQuestion;