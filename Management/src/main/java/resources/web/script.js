// =============================
// CONFIGURAÇÃO BASE
// =============================
const API_URL = "http://localhost:8080/api/pessoas";
const responseBox = document.getElementById("responseBox");

function mostrarResposta(data) {
  responseBox.textContent = JSON.stringify(data, null, 2);
}

// =============================
// CONTATOS DINÂMICOS
// =============================
function adicionarContato(valor = "") {
  const container = document.getElementById("contatosContainer");
  const div = document.createElement("div");
  div.classList.add("form-row");
  div.innerHTML = `
    <input type="text" placeholder="Tipo (e.g. EMAIL, TELEFONE)" class="tipoContato" required>
    <input type="text" placeholder="Valor" class="valorContato" value="${valor}" required>
    <button type="button" class="delete" onclick="this.parentElement.remove()">Remover</button>
  `;
  container.appendChild(div);
}

// =============================
// ENDEREÇOS DINÂMICOS
// =============================
function adicionarEndereco() {
  const container = document.getElementById("enderecosContainer");
  const div = document.createElement("div");
  div.classList.add("form-row");
  div.innerHTML = `
    <input type="text" placeholder="Rua" class="ruaEndereco" required>
    <input type="text" placeholder="Número" class="numeroEndereco" required>
    <input type="text" placeholder="Cidade" class="cidadeEndereco" required>
    <input type="text" placeholder="Estado" class="estadoEndereco" required>
    <button type="button" class="delete" onclick="this.parentElement.remove()">Remover</button>
  `;
  container.appendChild(div);
}

// =============================
// CADASTRAR (POST)
// =============================
document.getElementById("formCadastrar").addEventListener("submit", async (e) => {
  e.preventDefault();

  const nome = document.getElementById("nome").value;
  const tipo = document.getElementById("tipoSanguineo").value;

  const contatos = Array.from(document.querySelectorAll("#contatosContainer .form-row")).map(row => ({
    tipo: row.querySelector(".tipoContato").value,
    valor: row.querySelector(".valorContato").value
  }));

  const enderecos = Array.from(document.querySelectorAll("#enderecosContainer .form-row")).map(row => ({
    rua: row.querySelector(".ruaEndereco").value,
    numero: row.querySelector(".numeroEndereco").value,
    cidade: row.querySelector(".cidadeEndereco").value,
    estado: row.querySelector(".estadoEndereco").value
  }));

  const body = { nome, tipoSanguineo: tipo, contatos, enderecos };

  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body)
  });

  mostrarResposta(await res.json());
});

// =============================
// LISTAR TODAS (GET)
// =============================
document.getElementById("btnListar").addEventListener("click", async () => {
  const res = await fetch(API_URL);
  const data = await res.json();
  mostrarResposta(data);

  const tbody = document.querySelector("#tabelaPessoas tbody");
  tbody.innerHTML = "";

  data.forEach(pessoa => {
    const contatos = pessoa.contatos?.map(c => `${c.tipo}: ${c.valor}`).join("<br>") || "-";
    const enderecos = pessoa.enderecos?.map(e => `${e.rua}, ${e.numero} - ${e.cidade}/${e.estado}`).join("<br>") || "-";

    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${pessoa.id}</td>
      <td>${pessoa.nome}</td>
      <td>${pessoa.tipoSanguineo}</td>
      <td>${contatos}</td>
      <td>${enderecos}</td>
      <td class="actions">
        <button class="update" onclick="preencherAlteracao(${pessoa.id}, '${pessoa.nome}', '${pessoa.tipoSanguineo}')">Editar</button>
        <button class="delete" onclick="excluirPessoa(${pessoa.id})">Excluir</button>
      </td>`;
    tbody.appendChild(tr);
  });
});

// =============================
// CONSULTAR POR ID (GET /:id)
// =============================
document.getElementById("btnConsultar").addEventListener("click", async () => {
  const id = document.getElementById("idConsulta").value;
  if (!id) return alert("Informe o ID para consulta.");

  const res = await fetch(`${API_URL}/${id}`);
  mostrarResposta(await res.json());
});

// =============================
// EXCLUIR POR ID (DELETE /:id)
// =============================
document.getElementById("btnExcluir").addEventListener("click", async () => {
  const id = document.getElementById("idConsulta").value;
  if (!id) return alert("Informe o ID para excluir.");

  const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  mostrarResposta(await res.json());
});

// =============================
// ALTERAR (PUT /:id)
// =============================
document.getElementById("formAlterar").addEventListener("submit", async (e) => {
  e.preventDefault();
  const id = document.getElementById("idAlterar").value;
  const nome = document.getElementById("nomeAlterar").value;
  const tipo = document.getElementById("tipoAlterar").value;

  const body = { nome, tipoSanguineo: tipo };

  const res = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body)
  });

  mostrarResposta(await res.json());
});

// =============================
// FUNÇÕES AUXILIARES
// =============================
function preencherAlteracao(id, nome, tipo) {
  document.getElementById("idAlterar").value = id;
  document.getElementById("nomeAlterar").value = nome;
  document.getElementById("tipoAlterar").value = tipo;
  window.scrollTo({ top: document.body.scrollHeight, behavior: "smooth" });
}

async function excluirPessoa(id) {
  const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  mostrarResposta(await res.json());
}
