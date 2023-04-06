import "./styles.css";


const AllCarsComp = ({ product }) => {
  const url = "https://carlux-grupo1.s3.us-east-2.amazonaws.com";
  return (
    <div className="product-card">
      <h2 className="car-title">{product.nameCar}</h2>
      <img src={url + product.urlImage} alt={product.nameCar} /> 
      <p>{product.description}</p>
      <p>R$ {product.price}</p>
    </div>
  );
};

export default AllCarsComp;
