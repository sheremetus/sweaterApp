<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
<#--   Определяем переменные, необходимые для проверки прав пользователей-->
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name  = user.getUsername()
    isAdmin = user.isAdmin()
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    >
</#if>