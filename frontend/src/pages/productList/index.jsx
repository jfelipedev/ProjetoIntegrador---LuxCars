import React from 'react';
import { useLocation } from 'react-router-dom';
import Header from '../../components/header/header';
import Footer from '../../components/footer/footer';
import SearchCarsList from '../../components/searchCarsList/searchCarsList';


function ProductListScreen() {

  const { state } = useLocation();
  console.log(state);


  return (
    <div>
      <Header />
      <SearchCarsList selectedCityId={state.selectedCityId} />
      <Footer />
    </div>
  )
}

export default ProductListScreen