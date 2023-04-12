import HomeCarrossel from "../../components/homeCarrossel/index";
import SearchCars from "../../components/searchCars/searchCars";
import Header from "../../components/header/header";
import Footer from "../../components/footer/footer";
import Category from "../../components/categorias/category";


function index() {
  return (
    <div className="index">
      <Header /> 
      <SearchCars />
      <HomeCarrossel />
      <Category />
      <Footer />
    </div>
  );
}

export default index;
