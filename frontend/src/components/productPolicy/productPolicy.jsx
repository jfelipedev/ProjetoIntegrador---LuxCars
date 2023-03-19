import React from 'react'
import './productPolicy.css'
import { productPolicyData } from './productPolicyData'

function ProductPolicy() {
  return (
    <div className='rentalPolicy'>
      <h1 className='policyTitle'>Política de Aluguel</h1>
      {productPolicyData.map(({title, text, text1, text11, text12, text13, text14, text2, text3, text4, text41, text42, text43, text44, text5 }) => {
        return(
          <div>
              <h3 className='secundTitle'>{title}</h3>
               
                <ul className='polictText'>
                  <li className='square'>{text}</li>
                  <li className='square'>{text1} </li>
                  <li className='circle'>{text11} </li>
                  <li className='circle'>{text12} </li>
                  <li className='circle'>{text13} </li>
                  <li className='circle'>{text14} </li>
                  <li className='square'>{text2} </li>
                  <li className='square'>{text3} </li>
                  <li className='square'>{text4} </li>
                  <li className='circle'>{text41} </li>
                  <li className='circle'>{text42} </li>
                  <li className='circle'>{text43} </li>
                  <li className='circle'>{text44} </li>
                  <li  className='square'>{text5} </li>
                </ul>
               
          </div>
        )
      })}
    </div>
  )
}

export default ProductPolicy