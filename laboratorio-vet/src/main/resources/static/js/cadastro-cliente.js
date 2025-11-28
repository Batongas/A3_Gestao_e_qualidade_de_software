// Função chamada pelo botão "Salvar" no HTML
async function enviar() {
    // 1Pegar os elementos do HTML
    const nome = document.getElementById("nome").value;
    const sobrenome = document.getElementById("sobrenome").value;
    const cpf = document.getElementById("cpf").value;
    const telefone = document.getElementById("telefone").value;
    const email = document.getElementById("email").value;
    const cep = document.getElementById("cep").value;
    const endereco = document.getElementById("endereco").value;
    const dataNascimento = document.getElementById("data").value;
    const genero = document.getElementById("genero").value;
    
    const divMsg = document.getElementById("msg");

    // validação simples (Opcional)
    if (!nome || !cpf || !telefone) {
        divMsg.innerHTML = `<p class="erro">Preencha os campos obrigatórios!</p>`;
        return;
    }

    divMsg.innerHTML = "Enviando...";

    // objeto JSON 
    const dadosTutor = {
        nome: nome,
        sobrenome: sobrenome,
        cpf: cpf,
        telefone: telefone,
        email: email,
        cep: cep,
        endereco: endereco,
        dataNascimento: dataNascimento, // YYYY-MM-DD
        generoTutor: genero // 'M' ou 'F'
    };

    try {
        // Enviar para a API (TutorController)
        const response = await fetch("/api/tutores/cadastrar", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dadosTutor)
        });

        // Ler a resposta
        if (response.ok) {
            const mensagemSucesso = await response.text(); // O Java retorna um texto com o ID
            divMsg.innerHTML = `<p class="sucesso">${mensagemSucesso}</p>`;
            
            
        } else {
            const mensagemErro = await response.text();
            divMsg.innerHTML = `<p class="erro">${mensagemErro}</p>`;
        }

    } catch (error) {
        console.error(error);
        divMsg.innerHTML = `<p class="erro">Erro ao conectar com o servidor.</p>`;
    }
}