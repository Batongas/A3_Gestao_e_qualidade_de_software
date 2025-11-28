document.addEventListener("DOMContentLoaded", () => {
    
    const btnSalvar = document.querySelector(".btn-vet");

    btnSalvar.addEventListener("click", async () => {
        
        // 1. Pegar os dados
        const tipoExame = document.getElementById("tipo").value;
        const resultado = document.getElementById("resultado").value;
        const idPaciente = document.getElementById("idPaciente").value;
        
        // DICA: Num sistema real, pegaríamos o ID do médico logado automaticamente.
        // Por enquanto, vamos usar o que for digitado no campo.
        const idFuncionario = document.getElementById("idFuncionario").value;

        const msg = document.getElementById("msg");
        msg.innerHTML = "Enviando...";

        // 2. Validação Simples
        if (!tipoExame || !idPaciente || !idFuncionario) {
            msg.innerHTML = `<p class="erro">Preencha os campos obrigatórios!</p>`;
            return;
        }

        // 3. Montar o JSON
        // Nota: Não enviamos a data, pois o Back-End define a data atual automaticamente.
        const dadosExame = {
            tipoExame: tipoExame,
            resultado: resultado,
            idPaciente: parseInt(idPaciente),     // Convertendo para número
            idFuncionario: parseInt(idFuncionario) // Convertendo para número
        };

        try {
            // 4. Enviar para a API
            const response = await fetch("/api/exames/cadastrar", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(dadosExame)
            });

            const textoResposta = await response.text();

            if (response.ok) {
                msg.innerHTML = `<p class="sucesso">${textoResposta}</p>`;
                
                // Limpar campos
                document.getElementById("tipo").value = "";
                document.getElementById("resultado").value = "";
                document.getElementById("idPaciente").value = "";
                // Não limpamos o médico para facilitar cadastrar vários seguidos
            } else {
                msg.innerHTML = `<p class="erro">${textoResposta}</p>`;
            }

        } catch (error) {
            console.error(error);
            msg.innerHTML = `<p class="erro">Erro de conexão.</p>`;
        }
    });
});