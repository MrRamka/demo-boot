<#import "/spring.ftl" as spring />

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>Registration</h1>
<div>
    <@spring.bind path= "form" />
    <#if form?? && noErrors??>
        Your submitted data<br>
        Full name: ${form.fullName}<br>
        Username: ${form.username}<br>
    <#else>
        <form method="post">
            Username:
            <br>
            <@spring.formInput "form.username" />
            <@spring.showErrors "<br>"/>
            <br>
            Full Name:
            <br>
            <@spring.formInput "form.fullName"/>
            <@spring.showErrors "<br>"/>
            <br>
            Password:<br>
            <@spring.formPasswordInput  "form.password"/>
            <@spring.showErrors "<br>"/>
            <br>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="submit" value="Registration">
        </form>
    </#if>

</div>
</body>
</html>