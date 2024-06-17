document.getElementById("buttonFeedBack").addEventListener("click", function(event) {
    event.preventDefault();

    var form = document.getElementById("feedback");
    var nameField = document.getElementById("name");
    var emailField = document.getElementById("email");
    var messageField = document.getElementById("message");

    // Сброс ошибок
    nameField.classList.remove("input-error");
    emailField.classList.remove("input-error");
    messageField.classList.remove("input-error");

    var hasError = false;

    // Проверка полей на пустоту
    if (nameField.value.trim() === "") {
        nameField.classList.add("input-error");
        hasError = true;
    }
    if (emailField.value.trim() === "") {
        emailField.classList.add("input-error");
        hasError = true;
    }
    if (messageField.value.trim() === "") {
        messageField.classList.add("input-error");
        hasError = true;
    }

    if (!hasError) {
        var formData = new FormData(form);
        var data = {
            name: formData.get('name'),
            email: formData.get('email'),
            message: formData.get('message')
        };

        fetch('/api/feedback', {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = '/api/index';
                } else {
                    console.error('Ошибка при отправке формы');
                }
            })
            .catch(error => {
                console.error('Ошибка:', error);
            });
    } else {
        console.log("Есть ошибки в форме");
    }
});