<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags always come first -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/css/bootstrap.css">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-light bg-faded">
        <a class="navbar-brand" href="#">Navbar</a>
    </nav>
    <#if content?has_content>
    <div class="row">
        <div class="col-sm-${content.cardInfo.numOfColls!4}">
            <div class="card-deck-wrapper">
                <div class="card-deck">
                <#list content.cardInfo.cardContent.cardList as list>
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">${list.title!}</h4>
                            <#if list.subtitle?has_content>
                                <h6 class="card-subtitle text-muted">${list.subtitle!}</h6>
                            </#if>
                        </div>
                        <img src="img/menukaart.jpg" alt="Card image">

                        <div class="card-block">
                            <p class="card-text">${list.text!} </p>
                            <a href="#" class="card-link">Lees verder</a>
                            <a href="#" class="card-link">Another link</a>
                        </div>
                    </div>
                </#list>
                </div>
            </div>
        </div>
    </div>
    </#if>
</div>

<!-- jQuery first, then Bootstrap JS. -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/js/bootstrap.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/holder/2.8.2/holder.js"></script>
</body>
</html>