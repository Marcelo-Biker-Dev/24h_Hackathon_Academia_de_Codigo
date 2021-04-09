$(document).ready(function() {

    $("form").submit(function(e) {

        e.preventDefault();
    });

    $("#submit").click(function(event) {

        $.ajax({

            url: 'http://localhost:8080/javabank5/api/customer',
            type: 'POST',
            data: JSON.stringify({
                firstName: $('#firstName').val(),
                lastName: $('#lastName').val(),
                email: $('#email').val(),
                phone: $('#phone').val(),

            }),
            async: true,
            contentType: 'application/json;charset=UTF-8',
            success: successCallback,
            error: errorCallback

        });
    });


    function errorCallback(request, status, error) {
        console.log(status + " error " + error.message)
    }

    function redirect() {
        window.location = "XMLindexJQuery.html"

    }

    function successCallback(response) {
        redirect();
    }

})