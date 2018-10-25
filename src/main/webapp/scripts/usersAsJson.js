$( document ).ready(function() {
    $.get( "json", function( data ) {
        var $result = $( ".result" );
        for(var i = 0; i < data.length; i++){
            $result.append("<p>Name: " + data[i].name + ", age:" + data[i].age + "</p>");
        }
    });
    $(".addUser").click(function (event) {
        event.preventDefault();
        var userName = $(".userName").val();
        var userAge = $(".userAge").val();
        var user ={
            name: userName,
            age: userAge,
        };
        var userString = JSON.stringify(user);
        $.post("json", {user: userString})
            .done(function() {
                window.location.href = "/jsonUsers";
            });
    });
});