<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Registro e Busca</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <style>
        body { 
            font-family: sans-serif; 
            max-width: 600px; 
            margin: auto; 
            padding: 20px; 
            background-color: #f4f4f4;
        }
        h1 {
            color: #333;
            border-bottom: 2px solid #007bff;
        }
        input[type="text"], input[type="email"], input[type="password"] {
            width: 98%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 10px 15px; 
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        
    
        .sucesso { background-color: #d4edda; color: #155724;}
        .erro { background-color: #f8d7da; color: #721c24;}
        #foto-resultado { max-width: 150px; max-height: 150px; border-radius: 5px; margin-top: 10px; }
    </style>
</head>
<body>

    <h1>Registre-se</h1>
    <form id="form-registro">
        <label for="username">Nome de Usuário:</label><br>
        <input type="text" id="username" required>
        <br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" size="30" required>
        <br><br>

        <label for="password">Senha:</label><br>
        <input type="password" id="password" required>
        <br><br>

        <label for="foto">Foto de Perfil:</label><br>
        <input type="file" id="foto" accept="image/*"> <br><br>

        <button type="submit">Registrar</button>
    </form>
    <div id="mensagem"></div>

    <hr>

    <h1>Buscar Usuário</h1>
    <form id="form-busca">
        </form>
    
    <div id="resultado-busca">
        <div id="dados-texto"></div>
        <img id="foto-resultado" src="" alt="Foto do Usuário">
    </div>


    <script>
        $(document).ready(function() {

            $("#form-registro").on("submit", function(event) {
                event.preventDefault();
                $("#mensagem").empty().removeClass();
                
                var dadosUsuario = {
                    username: $("#username").val(),
                    email: $("#email").val(),
                    password: $("#password").val(),
                    fotoBase64: null
                };

                var arquivo = $("#foto")[0].files[0];
                
                if (arquivo) {
                    var reader = new FileReader();
                    
                    reader.onload = function(e) {
                        dadosUsuario.fotoBase64 = e.target.result;
                        
                        enviarFormulario(dadosUsuario);
                    };
                    
                    reader.readAsDataURL(arquivo);
                } else {
                    enviarFormulario(dadosUsuario);
                }
            });

            function enviarFormulario(dadosJSON) {
                $.ajax({
                    url: "usuarios",
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(dadosJSON),
                    
                    success: function(response) {
                        $("#mensagem").text(response).addClass("sucesso");
                        $("#form-registro")[0].reset();
                    },
                    error: function(jqXHR) {
                        $("#mensagem").text(jqXHR.responseText).addClass("erro");
                    }
                });
            }


            $("#form-busca").on("submit", function(event) {
                event.preventDefault();
                $("#resultado-busca").removeClass();
                $("#dados-texto").empty();
                $("#foto-resultado").hide().attr("src", "");
                
                var usernameParaBuscar = $("#username-busca").val();

                $.ajax({
                    url: "usuarios",
                    type: "GET",
                    data: { username: usernameParaBuscar },
                    
                    success: function(response) {
                        var texto = "<h3>Usuário Encontrado:</h3>";
                        texto += "<pre>" + JSON.stringify(response, null, 2) + "</pre>";
                        
                        $("#dados-texto").html(texto);
                        $("#resultado-busca").addClass("sucesso");

                        if (response.fotoBase64) {
                            $("#foto-resultado").attr("src", response.fotoBase64).show();
                        }
                    },
                    error: function(jqXHR) {
                        var textoErro = "Erro ao buscar: " + jqXHR.responseText;
                        $("#dados-texto").html(textoErro);
                        $("#resultado-busca").addClass("erro");
                    }
                });
            }); 
        });
    </script>

</body>
</html>