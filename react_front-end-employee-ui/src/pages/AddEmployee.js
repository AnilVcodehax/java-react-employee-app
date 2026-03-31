import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../api/api";

function AddEmployee() {
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [email, setEmail] = useState("");

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Submitting ... ", firstname,lastname,email);

    API.post("/api/employees", { firstname, lastname, email })
      .then(() => {
        alert("Employee added successfully-Anil");
        navigate("/employees");
      })
      .catch((err) => {
        alert("Error adding employee-Anil");
        console.log(err);
        
      });
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Add Employee</h2>

      <form onSubmit={handleSubmit}>
        <input
          
          placeholder="First Name"
          value={firstname}
          onChange={(e) => setFirstname(e.target.value)}
          
        />
        <br /><br />

        <input
          
          placeholder="Last Name"
          value={lastname}
          onChange={(e) => setLastname(e.target.value)}
          
        />
        <br /><br />

        <input
          
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          
        />
        <br /><br />

        <button type="submit">Save</button>
      </form>
    </div>
  );
}

export default AddEmployee;