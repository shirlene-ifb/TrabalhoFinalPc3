<template>
  <div class="list row">
    <div class="col-md-6">
      <h4>Lista de Produtos</h4>
      <ul>
        <li v-for="(produto, index) in produtos" :key="index">
          <router-link
            :to="{
name: 'produto-detalhes',
params: { produto: produto, id: produto.id }
}"
          >{{produto.nome}}</router-link>
        </li>
      </ul>
    </div>
    <div class="col-md-6">
      <router-view @refreshData="refreshList"></router-view>
    </div>
  </div>
</template>
<script>
import http from "../http-common";
export default {
  name: "produtos-lista",
  data() {
    return {
      produtos: []
    };
  },
  methods: {
    recuperaProdutos() {
      http
        .get("/produtos")
        .then(response => {
          this.produtos = response.data; // JSON are parsed automatically.
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.recuperaProdutos();
    }
  },
  mounted() {
    this.recuperaProdutos();
  }
};
</script>
<style>
.list {
  text-align: left;
  max-width: 450px;
  margin: auto;
}
</style>