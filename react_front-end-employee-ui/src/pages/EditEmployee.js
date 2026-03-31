import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import API from "../api/api";

function EditEmployee() {
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [email, setEmail] = useState("");

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    API.get(`/api/employees/${id}`)
      .then((res) => {
        setFirstname(res.data.firstname);
        setLastname(res.data.lastname);
        setEmail(res.data.email);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [id]);

  const handleUpdate = (e) => {
    e.preventDefault();

    API.put(`/api/employees/${id}`, { firstname,lastname, email })
      .then(() => {
        alert("Employee updated successfully-Anil");
        navigate("/employees");
      })
      .catch((err) => {
        console.log(err);
        alert("Update failed-Anil");
      });
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Edit Employee</h2>

      <form onSubmit={handleUpdate}>
        <input
          type="text"
          value={firstname}
          onChange={(e) => setFirstname(e.target.value)}
          required
        />
        <br /><br />

        <input
          type="text"
          value={lastname}
          onChange={(e) => setLastname(e.target.value)}
          required
        />
        <br /><br />

        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <br /><br />

        <button type="submit">Update</button>
      </form>
    </div>
  );
}

export default EditEmployee;