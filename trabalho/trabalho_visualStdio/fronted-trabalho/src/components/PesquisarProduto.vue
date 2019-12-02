<template>
  <div class="searchform">
    <h4>Busca por id</h4>
    <div class="form-group">
      <input type="number" class="form-control" id="id" required v-model="id" name="id" />
    </div>
    <div class="btn-group">
      <button v-on:click="pesquisarProdutos" class="btn btn-success">Pesquisar</button>
    </div>
    <ul class="search-result">
      <li v-for="(produto, index) in produtos" :key="index">
        <h6>{{produto.nome}} ({{produto.id}})</h6>
      </li>
    </ul>
  </div>
</template>
<script>
import http from "../http-common";
export default {
  name: "pesquisar-produto",
  data() {
    return {
      id: 0,
      produtos: []
    };
  },
  methods: {
    pesquisarProdutos() {
      http
        .get("/produtos/id/" + this.id)
        .then(response => {
          this.produtos = response.data; // JSON are parsed automatically.
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>
<style>
.searchform {
  max-width: 300px;
  margin: auto;
}
.search-result {
  margin-top: 20px;
  text-align: left;
}
</style>