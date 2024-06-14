document.addEventListener("DOMContentLoaded", () => {
    const studentList = document.getElementById("student-list");
    const addStudentForm = document.getElementById("add-student-form");
    const getStudentForm = document.getElementById("get-student-form");
    const deleteStudentForm = document.getElementById("delete-student-form");
    const updateStudentForm = document.getElementById("update-student-form");
    const studentDetails = document.getElementById("student-details");

    const apiUrl = "http://localhost:8000/estudante";

    // Fetch all students
    function fetchStudents() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                studentList.innerHTML = '';
                data.forEach(student => {
                    const li = document.createElement("li");
                    li.textContent = `${student.id}: ${student.name}`;
                    studentList.appendChild(li);
                });
            })
            .catch(error => console.error("Error fetching students:", error));
    }

    // Add a new student
    addStudentForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const studentName = document.getElementById("student-name").value.trim();

        if (!studentName) {
            alert("Please enter a student name.");
            return;
        }

        fetch(apiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name: studentName })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(() => {
            fetchStudents();
            addStudentForm.reset();
        })
        .catch(error => console.error("Error adding student:", error));
    });

    // Get student by ID
    getStudentForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const studentId = document.getElementById("student-id").value.trim();

        fetch(`${apiUrl}/${studentId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                studentDetails.textContent = data ? `${data.id}: ${data.name}` : "Student not found";
            })
            .catch(error => console.error("Error fetching student:", error));
    });

    // Delete student by ID
    deleteStudentForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const studentId = document.getElementById("delete-student-id").value.trim();

        fetch(`${apiUrl}/${studentId}`, {
            method: "DELETE"
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.text(); // Fetch API returns a promise that resolves to a string
        })
        .then(() => {
            fetchStudents();
            deleteStudentForm.reset();
        })
        .catch(error => console.error("Error deleting student:", error));
    });

    // Update student by ID
    updateStudentForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const studentId = document.getElementById("update-student-id").value.trim();
        const studentName = document.getElementById("update-student-name").value.trim();

        if (!studentId || !studentName) {
            alert("Please enter both student ID and name.");
            return;
        }

        fetch(`${apiUrl}/${studentId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ id: studentId, name: studentName })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(() => {
            fetchStudents();
            updateStudentForm.reset();
        })
        .catch(error => console.error("Error updating student:", error));
    });

    // Initial fetch of students
    fetchStudents();
});
