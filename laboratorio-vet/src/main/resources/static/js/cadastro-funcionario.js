async function cadastrar() {
    // 1. Pegar os valores
    const nomeCompleto = document.getElementById("nome").value;
    let crmv = document.getElementById("crmv").value; // Usamos let para poder mudar
    const cargo = document.getElementById("cargo").value;
    const login = document.getElementById("login").value;
    const senhaPura = document.getElementById("senha").value;
    
    const divMsg = document.getElementById("msg");
    divMsg.innerHTML = "Enviando...";

    // --- A MELHORIA CRUCIAL AQUI ---
    // Se o CRMV estiver vazio, transformamos em NULL
    // Isso garante que a regra do Banco de Dados funcione para recepcionistas
    if (crmv.trim() === "") {
        crmv = null;
    }

    try {
        const response = await fetch("/api/funcionarios/cadastrar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                nomeCompleto,
                crmv,
                cargo,
                login,
                senhaPura,
            }),
        });

        const texto = await response.text();

        if (response.ok) {
            divMsg.innerHTML = `<p class="sucesso">${texto}</p>`;
            // Limpar campos após sucesso
            document.querySelectorAll('input').forEach(input => input.value = '');
        } else {
            divMsg.innerHTML = `<p class="erro">${texto}</p>`;
        }
    } catch (error) {
        console.error(error);
        divMsg.innerHTML = `<p class="erro">Erro ao conectar com o servidor.</p>`;
    }
}