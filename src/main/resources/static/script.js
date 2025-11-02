const BASE_URL = "http://127.0.0.1:8081/api";

async function getEmployees() {
  const response = await fetch(`${BASE_URL}/employees`);
  const employees = await response.json();
  const tbody = document.querySelector("#employeeTable tbody");
  tbody.innerHTML = "";

  employees.forEach(emp => {
    const row = `
      <tr>
        <td>${emp.empId}</td>
        <td>${emp.firstName}</td>
        <td>${emp.lastName}</td>
        <td>${emp.email}</td>
        <td><button onclick="deleteEmployee(${emp.empId})">Delete</button></td>
      </tr>
    `;
    tbody.innerHTML += row;
  });
}

async function addEmployee() {
  const firstName = document.getElementById("firstName").value;
  const lastName = document.getElementById("lastName").value;
  const email = document.getElementById("email").value;

  await fetch(`${BASE_URL}/employee`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ firstName, lastName, email })
  });

  alert("Employee added!");
  getEmployees();
}

async function updateEmployee() {
  const empId = document.getElementById("empId").value;
  const firstName = document.getElementById("firstName").value;
  const lastName = document.getElementById("lastName").value;
  const email = document.getElementById("email").value;

  await fetch(`${BASE_URL}/employee`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ empId, firstName, lastName, email })
  });

  alert("Employee updated!");
  getEmployees();
}

async function deleteEmployee(id) {
  if (!confirm("Delete this employee?")) return;

  await fetch(`${BASE_URL}/employee/${id}`, { method: "DELETE" });
  alert("Employee deleted!");
  getEmployees();
}

window.onload = getEmployees;
