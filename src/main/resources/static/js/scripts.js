document.getElementById("buttonFeedBack").addEventListener("click", function(event) {
    // Предотвращаем отправку формы
    event.preventDefault();

    // Получаем форму по ее ID
    var form = document.getElementById("feedback");

    // Получаем элементы полей ввода
    var nameField = document.getElementById("name");
    var emailField = document.getElementById("email");
    var messageField = document.getElementById("message");

    // Получаем значения полей
    var name = nameField.value;
    var email = emailField.value;
    var message = messageField.value;

    // Сбрасываем классы ошибок
    nameField.classList.remove("input-error");
    emailField.classList.remove("input-error");
    messageField.classList.remove("input-error");

    // Флаг наличия ошибок
    var hasError = false;

    // Проверяем поля на пустоту и добавляем класс ошибки, если нужно
    if (name.trim() === "") {
        nameField.classList.add("input-error");
        hasError = true;
    }
    if (email.trim() === "") {
        emailField.classList.add("input-error");
        hasError = true;
    }
    if (message.trim() === "") {
        messageField.classList.add("input-error");
        hasError = true;
    }

    // Если нет ошибок, можно отправить данные на сервер
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
        // Если есть ошибки, логируем их в консоль
        console.log("Есть ошибки в форме");
    }
});


