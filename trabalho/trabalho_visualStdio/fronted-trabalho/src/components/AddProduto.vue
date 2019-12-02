<template>
  <div class="submitform">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="nome">Nome</label>
        <input
          type="text"
          class="form-control"
          id="nome"
          required
          v-model="produto.nome"
          name="nome"
        />
      </div>
      <div class="form-group">
        <label for="descricao">Descricao</label>
        <input
          type="text"
          class="form-control"
          id="descricao"
          required
          v-model="produto.descricao"
          name="descricao"
        />
      </div>
      <div class="form-group">
        <label for="desconto">Desconto</label>
        <input
          type="number"
          class="form-control"
          id="desconto"
          required
          v-model="produto.desconto"
          name="desconto"
        />
      </div>
      <div class="form-group">
        <label for="fabricante">Fabricante</label>
        <input
          type="text"
          class="form-control"
          id="fabricante"
          required
          v-model="produto.fabricante"
          name="fabricante"
        />
      </div>
      <div class="form-group">
        <label for="garantia">Garantia</label>
        <input
          type="text"
          class="form-control"
          id="garantia"
          required
          v-model="produto.garantia"
          name="garantia"
        />
      </div>
      <div class="form-group">
        <label for="imagem">Imagem</label>
        <input
          type="text"
          class="form-control"
          id="imagem"
          required
          v-model="produto.imagem"
          name="imagem"
        />
      </div>
      <div class="form-group">
        <label for="preco_compra">Preco_Compra</label>
        <input
          type="number"
          class="form-control"
          id="preco_compra"
          required
          v-model="produto.preco_compra"
          name="preco_compra"
        />
      </div>
      <div class="form-group">
        <label for="preco_venda">Preco_Venda</label>
        <input
          type="number"
          class="form-control"
          id="preco_venda"
          required
          v-model="produto.preco_venda"
          name="preco_venda"
        />
      </div>
      <button v-on:click="salvarProduto" class="btn btn-success">Salvar</button>
    </div>
    <div v-else>
      <h4>Salvo com sucesso.</h4>
      <button class="btn btn-success" v-on:click="novoProduto">Adicionar</button>
    </div>
  </div>
</template>
<script>
import http from "../http-common";
export default {
  name: "add-produto",
  data() {
    return {
      produto: {
        id: 0,
        nome: "",
        descricao: "",
        desconto: 0,
        fabricante: "",
        garantia: "",
        imagem: "",
        preco_compra: 0,
        preco_venda: 0
      },
      submitted: false
    };
  },
  methods: {
    salvarProduto() {
      var data = {
        nome: this.produto.nome,
        descricao: this.produto.descricao,
        desconto: this.produto.desconto,
        fabricante: this.produto.fabricante,
        garantia: this.produto.garantia,
        imagem: this.produto.imagem,
        preco_compra: this.produto.preco_compra,
        preco_venda: this.produto.preco_venda
      };
      http
        .post("/produto", data)
        .then(response => {
          this.produto.id = response.data.id;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    novoProduto() {
      this.submitted = false;
      this.produto = {};
    }
  }
};
</script>
<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>