import React from 'react'
import './productPolicy.css'
import { productPolicyData } from './productPolicyData'

function ProductPolicy() {
  return (
    <div className='rentalPolicy'>
      <h1 className='policyTitle'>Política de Aluguel</h1>
      <div className='policyContainer'>
        {productPolicyData.map(({ title, text, text1, text11, text12, text13, text2, text3, text4, text41, text42, text5 }) => {
          return (
            <div className='policyItem'>
              <h3 className='secundTitle'>{title}</h3><br />
              <ul className='polictText'>
                <li className='square'>{text}</li>
                <li className='square'>{text1} </li>
                <li className='square'>{text11} </li>
                <li className='square'>{text12} </li>
                <li className='square'>{text13} </li>
                <li className='square'>{text2} </li>
                <li className='square'>{text3} </li>
                <li className='square'>{text4} </li>
                <li className='square'>{text41} </li>
                <li className='square'>{text42} </li>
                <li className='square'>{text5} </li>
              </ul>

            </div>
          )
        })}
      </div>
    </div>
  )
}

export default ProductPolicy
