// js/login.js

document.addEventListener("DOMContentLoaded", () => {
    
    const btnEntrar = document.querySelector(".btn-vet"); // Pega o botão pela classe

    // Adiciona o evento de clique
    btnEntrar.addEventListener("click", async () => {
        
        const login = document.getElementById("login").value;
        const senha = document.getElementById("senha").value;
        const msg = document.getElementById("msg");

        // Limpa mensagens anteriores
        msg.innerHTML = "";

        // Validação simples visual
        if (!login || !senha) {
            msg.innerHTML = `<p class="erro">Preencha todos os campos!</p>`;
            return;
        }

        try {
            const response = await fetch("/api/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ login, senha }),
            });

            if (response.ok) {
                const funcionario = await response.json();
                
                // Feedback visual de sucesso
                msg.innerHTML = `<p class="sucesso">Bem-vindo, ${funcionario.nomeCompleto}!</p>`;
                
                // Salva na sessão (mantendo a chave "usuario" que você usou na home)
                localStorage.setItem("usuario", JSON.stringify(funcionario));

                // Redireciona após um tempinho para o usuário ler a mensagem
                setTimeout(() => {
                    window.location.href = "home.html";
                }, 1000);
                
            } else {
                // Se o login falhar, mostra o erro que veio da API
                const erro = await response.text(); // Pega o texto "Login ou senha inválidos"
                msg.innerHTML = `<p class="erro">${erro}</p>`;
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
            msg.innerHTML = `<p class="erro">Erro de conexão com o servidor.</p>`;
        }
    });
});