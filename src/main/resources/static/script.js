const BASE_URL = "http://127.0.0.1:8081/api";
const EMPLOYEE_API = `${BASE_URL}/employees`;
const PAYSLIP_API = `${BASE_URL}/payslips`;

// --- Employee functions ---

async function getEmployees() {
  try {
    const response = await fetch(EMPLOYEE_API);
    if (!response.ok) throw new Error("Failed to fetch employees");
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
  } catch (error) {
    alert(error.message);
  }
}

async function addEmployee() {
  const firstName = document.getElementById("firstName").value.trim();
  const lastName = document.getElementById("lastName").value.trim();
  const email = document.getElementById("email").value.trim();
  const basicSalary = parseFloat(document.getElementById("basicSalary").value.trim());
  const designation = document.getElementById("designation").value.trim();

  if (!firstName || !lastName || !email || isNaN(basicSalary) || !designation) {
    alert("Please fill all employee fields, including Basic Salary and Designation");
    return;
  }

  try {
    const response = await fetch(EMPLOYEE_API, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ firstName, lastName, email, basicSalary, designation }),
    });
    if (!response.ok) throw new Error("Failed to add employee");

    alert("Employee added!");
    clearEmployeeForm();
    getEmployees();
  } catch (error) {
    alert(error.message);
  }
}

async function updateEmployee() {
  const empId = document.getElementById("empId").value.trim();
  const firstName = document.getElementById("firstName").value.trim();
  const lastName = document.getElementById("lastName").value.trim();
  const email = document.getElementById("email").value.trim();
  const basicSalary = parseFloat(document.getElementById("basicSalary").value.trim());
  const designation = document.getElementById("designation").value.trim();

  if (!empId || !firstName || !lastName || !email || isNaN(basicSalary) || !designation) {
    alert("Please fill all fields including Employee ID, Basic Salary and Designation for update");
    return;
  }

  try {
    const response = await fetch(`${EMPLOYEE_API}/${empId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ empId: Number(empId), firstName, lastName, email, basicSalary, designation }),
    });
    if (!response.ok) throw new Error("Failed to update employee");

    alert("Employee updated!");
    clearEmployeeForm();
    getEmployees();
  } catch (error) {
    alert(error.message);
  }
}

function clearEmployeeForm() {
  document.getElementById("empId").value = "";
  document.getElementById("firstName").value = "";
  document.getElementById("lastName").value = "";
  document.getElementById("email").value = "";
  document.getElementById("basicSalary").value = "";
  document.getElementById("designation").value = "";
}

async function deleteEmployee(empId) {
  if (!confirm(`Are you sure you want to delete employee ID ${empId}?`)) return;

  try {
    const response = await fetch(`${EMPLOYEE_API}/${empId}`, {
      method: 'DELETE',
    });
    if (!response.ok) throw new Error('Failed to delete employee');
    alert('Employee deleted!');
    getEmployees();
  } catch (error) {
    alert(error.message);
  }
}



// --- Payslip functions ---

async function generatePayslip() {
  const empId = document.getElementById("payslipEmpId").value.trim();
  const month = document.getElementById("payslipMonth").value.trim();

  if (!empId || !month) {
    alert("Please enter Employee ID and Month.");
    return;
  }

  try {
    const response = await fetch(`${PAYSLIP_API}/generate/${empId}/${month}`, { method: "POST" });
    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(errorText || "Failed to generate payslip");
    }

    const payslip = await response.json();
    alert(`Payslip generated for employee ${empId} for ${month}.`);
    getPayslips(); // Refresh payslip list
  } catch (error) {
    alert("Error: " + error.message);
  }
}

async function getPayslips() {
  const empId = document.getElementById("listPayslipEmpId").value.trim();

  if (!empId) {
    alert("Please enter Employee ID.");
    return;
  }

  try {
    const response = await fetch(`${PAYSLIP_API}/employee/${empId}`);
    if (!response.ok) throw new Error("Failed to fetch payslips");

    const payslips = await response.json();
    const tbody = document.querySelector("#payslipTable tbody");
    tbody.innerHTML = "";

    if (payslips.length === 0) {
      tbody.innerHTML = `<tr><td colspan="8">No payslips found for employee ${empId}.</td></tr>`;
      return;
    }

    payslips.forEach(ps => {
      const genDate = new Date(ps.generatedDate);
      const row = `
        <tr>
          <td>${ps.payslipId}</td>
          <td>${ps.month}</td>
          <td>${ps.basicSalary.toFixed(2)}</td>
          <td>${ps.hra.toFixed(2)}</td>
          <td>${ps.allowances.toFixed(2)}</td>
          <td>${ps.deductions.toFixed(2)}</td>
          <td>${ps.netSalary.toFixed(2)}</td>
          <td>${genDate.toLocaleDateString()}</td>
        </tr>
      `;
      tbody.innerHTML += row;
    });
  } catch (error) {
    alert(error.message);
  }
}

// Load employees on page load
window.onload = () => {
  getEmployees();
};
