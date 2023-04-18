function displayStudents() {
    fetch('http://localhost:8080/api/students', {
        headers: {
            'Authorization': 'Basic ' + authEncoded
        }
    })
        .then(response => response.json())
        .then(data => {
            const ul = document.querySelector('#students-list');

            ul.innerHTML = '';
            const li = document.createElement('li');
            li.textContent = 'Students list:';
            ul.appendChild(li);

            data.forEach(student => {
                const li = document.createElement('li');
                li.textContent = `ID: ${student.id}, Name: ${student.name}, Email: ${student.email}`;
                ul.appendChild(li);
              
                const button = document.createElement('button');
                button.textContent = 'Fetch image';
                button.addEventListener('click', () => {
                  fetch(`http://localhost:8080/static/studentImages/${student.imageName}`, {
                    headers: {
                        'Authorization':'Basic ' + authEncoded
                    }
                  })
                    .then(response => {
                    response.blob();
                    }).then(blob => {
                        const img = document.createElement('img');
                        img.src = URL.createObjectURL(blob);
                        document.body.appendChild(img);
                      })
                    .catch(error => {
                      console.error('There was a problem with the fetch operation:', error);
                    });
                });
                li.appendChild(button);
              });
              
        })
        .catch(error => {
            console.error('Error getting the students list: ', error);
        })
}



const username = 'admin';
const password = 'new_password';
const authEncoded = btoa(username + ':' + password);

displayStudents();
const submitStudentsBtn = document.getElementById('submit-btn');
const updateStudentBtn = document.getElementById('update-btn');
const deleteStudentBtn = document.getElementById('delete-btn');


submitStudentsBtn.addEventListener('click', () => {
    const idInput = document.getElementById('id-input').value;
    const nameInput = document.getElementById('name-input').value;
    const emailInput = document.getElementById('email-input').value;
    const imageFile = document.getElementById('image-input').files[0];




    // const formData = {
    //     id: idInput,
    //     name: nameInput,
    //     email: emailInput
    // };
    const formData = new FormData();
    formData.append("id", idInput);
    formData.append("name", nameInput);
    formData.append("email", emailInput);
    formData.append("image", imageFile);




    fetch('http://localhost:8080/api/studentImages', {
        method: 'POST',
        headers: {
            'Authorization': 'Basic ' + authEncoded
        },
        body: formData
    }).then(() => {
        // Wait for 1 second
        setTimeout(function () {
            displayStudents();
        }, 100); 
    })
        .catch(error => console.error(error));
});

updateStudentBtn.addEventListener('click', () => {
    const idInput = document.getElementById('id-input').value;
    const nameInput = document.getElementById('name-input').value;
    const emailInput = document.getElementById('email-input').value;

    const formData = {
        id: idInput,
        name: nameInput,
        email: emailInput
    };

    fetch('http://localhost:8080/api/students', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + authEncoded
        },
        body: JSON.stringify(formData)
    })
        .catch(error => console.error(error));

    displayStudents();
});

deleteStudentBtn.addEventListener('click', () => {
    const idInput = document.getElementById('id-input').value;



    fetch('http://localhost:8080/api/students' + idInput, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Basic' + authEncoded
        }
    })
        .catch(error => console.error(error));

    displayStudents();
});