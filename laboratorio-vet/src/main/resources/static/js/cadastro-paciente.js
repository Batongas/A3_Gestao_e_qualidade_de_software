document.addEventListener("DOMContentLoaded", () => {
    const btnSalvar = document.querySelector(".btn-vet");

    btnSalvar.addEventListener("click", async () => {
        const nome = document.getElementById("nome").value;
        const especie = document.getElementById("especie").value;
        const raca = document.getElementById("raca").value;
        const pelagem = document.getElementById("pelagem").value;
        const peso = document.getElementById("peso").value;
        const genero = document.getElementById("genero").value;
        const dataNascimento = document.getElementById("dataNascimento").value;
        const idTutor = document.getElementById("idTutor").value;
        
        const msg = document.getElementById("msg");
        msg.innerHTML = "Enviando...";

        if (!nome || !idTutor) {
            msg.innerHTML = `<p class="erro">Nome e ID do Tutor são obrigatórios!</p>`;
            return;
        }

        const dados = {
            nomeAnimal: nome,
            especie: especie,
            raca: raca,
            pelagem: pelagem,
            peso: peso,
            genero: genero,
            dataNascimento: dataNascimento,
            idTutor: parseInt(idTutor)
        };

        try {
            const response = await fetch("/api/pacientes/cadastrar", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(dados)
            });

            const texto = await response.text();

            if (response.ok) {
                msg.innerHTML = `<p class="sucesso">${texto}</p>`;
                // Limpar campos principais
                document.getElementById("nome").value = "";
            } else {
                msg.innerHTML = `<p class="erro">${texto}</p>`;
            }
        } catch (error) {
            console.error(error);
            msg.innerHTML = `<p class="erro">Erro de conexão.</p>`;
        }
    });
});