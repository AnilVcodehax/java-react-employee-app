import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../api/api";
import { jwtDecode } from "jwt-decode";

function Employees() {
  const [employees, setEmployees] = useState([]);
  const [username, setUsername] = useState("");
  const [role, setRole] = useState("");

  const [page, setPage] = useState(0);
  const [totalPages,setTotalPages] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) return;
    
    const decoded = jwtDecode(token);
    console.log(decoded);

    setUsername(decoded.sub);
    setRole(decoded.role);

    // Only ADMIN fetch employees
    if (decoded.role !== "ADMIN") return;

    API.get(`/api/employees?page=${page}&size=5`)
      .then((res) => {
        console.log(res.data);
        
        if (Array.isArray(res.data)) {
          setEmployees(res.data);
          setTotalPages(1);
        } else {
          setEmployees(res.data.content || []);
          setTotalPages(res.data.totalPages || 1);
        }
      });
  //     .catch((err) => console.log(err));
  }, [page,role]);

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  const handleDelete = (id) => {
    if (window.confirm("Are you sure you want to delete?")) {
      API.delete(`/api/employees/${id}`)
        .then(() => {
          alert("Deleted successfully");
          setEmployees((prev) =>
            prev.filter((emp) => emp.id !== id)
          );
        })
        .catch((err) => {
          alert("Delete failed");
          console.log(err);
        });
    }
  };

  return (
    <div style={{ padding: "20px",backgroundColor: "rgba(45, 234, 206, 0.24)", color: "#190303" }}>
      <h2>Employees</h2>

      <p>Logged in as: {username}</p>
      <p>Role: {role}</p>

      <button
        onClick={handleLogout}
        style={{ marginBottom: "10px",backgroundColor: "rgb(224, 191, 24)", color: "#fff" }}
      >
        Logout - Anil
      </button>

      {/* Not Admin */}
      {role !== "ADMIN" && (
        <p style={{ color: "red" }}>
          You are not authorized to view employees
        </p>
      )}

      {/* Admin View */}
      {role === "ADMIN" && (
        <>
          <button
            onClick={() => navigate("/add")}
            style={{ marginBottom: "15px",backgroundColor: "rgb(36, 247, 89)", color: "#fff" }}
          >
            Add Employee
          </button>

          <table
            border="1"
            cellPadding="10"
            style={{
              borderCollapse: "collapse",
              width: "100%",
              backgroundColor: "#f9f9f9",
            }}
          >
            <thead>
              <tr>
                <th style={{ backgroundColor: "rgb(76, 190, 232)", color: "#fff" }}>
                  ID
                </th>
                <th style={{ backgroundColor: "rgb(76, 190, 232)", color: "#fff" }}>First Name</th>
                <th style={{ backgroundColor: "rgb(76, 190, 232)", color: "#fff" }}>Last Name</th>
                <th style={{ backgroundColor: "rgb(76, 190, 232)", color: "#fff" }}>Email</th>
                <th style={{ backgroundColor: "rgb(76, 190, 232)", color: "#fff" }}>Actions</th>
              </tr>
            </thead>

            <tbody>
              {employees.map((emp) => (
                <tr key={emp.id}>
                  <td>{emp.id}</td>
                  <td>{emp.firstname}</td>
                  <td>{emp.lastname}</td>
                  <td>{emp.email}</td>
                  <td>
                    <button
                      onClick={() => navigate(`/edit/${emp.id}`)}
                      style={{  marginLeft: "10px",  backgroundColor: "rgb(199, 152, 11)", color: "#141313"  }}
                    >
                    
                      Edit
                    </button>

                    <button 
                      onClick={() => handleDelete(emp.id)}
                      style={{  marginLeft: "10px",  backgroundColor: "rgb(232, 29, 29)", color: "#141313"  }}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <div style={{}}>
            <button
              disabled={page ===0}
              onClick={()=> setPage(page - 1)}
              style={{  marginLeft: "10px",  backgroundColor: "rgb(219, 66, 168)", color: "#141313"  }}
            >
              ⬅ Prev
            </button>

            <span style={{margin: "0 10px"}}>
              Page {page + 1 } of {totalPages}
            </span>

            <button
              disabled={page ===totalPages - 1}
              onClick={()=> setPage(page + 1)}
              style={{  marginLeft: "10px",  backgroundColor: "rgb(219, 66, 168)", color: "#141313"  }}
            >
              Next ➡
            </button>
          </div>
        </>
      )}
    </div>
  );
}

export default Employees;