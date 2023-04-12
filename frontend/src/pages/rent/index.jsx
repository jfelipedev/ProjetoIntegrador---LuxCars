import React from 'react'
import Header from '../../components/header/header'
import Footer from '../../components/footer/footer'
import RentComp from '../../components/rentMain/rent'
import { useLocation } from 'react-router-dom'


function RentScreen() {

  const {state} = useLocation();
  console.log(state)

  return (
    <div>
     <Header />
     <RentComp />
     <Footer />
    </div>
  )
}

export default RentScreen
