import "./searchCars.css";
import Select from "react-select";

const suppliers = [
  { label: "Conversivel", value: "Conversivel" },
  { label: "Coupé", value: "Coupé" },
  { label: "Esportivo", value: "Esportiv" },
  { label: "Sedan", value: "Sedan" },
];

const DBsuppliers = ["Buenos Aires", "Mendoza", "Cordoba"];

function SearchCars() {
  return (
    <div className="searchSection">
      <h1 className="sectionTitle">ALUGUE O CARRO DOS SEUS SONHOS</h1>
    </div>
  );
}

export default SearchCars;
