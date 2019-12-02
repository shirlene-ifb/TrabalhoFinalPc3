<template>
  <div v-if="this.produto">
    <h4>Produto</h4>
    <div>
      <label>Nome:</label>
      {{this.produto.nome}}
    </div>
    <div>
      <label>Descricao:</label>
      {{this.produto.descricao}}
    </div>
    <div>
      <label>Desconto:</label>
      {{this.produto.desconto}}
    </div>
    <div>
      <label>Fabricante:</label>
      {{this.produto.fabricante}}
    </div>
    <div>
      <label>Garantia:</label>
      {{this.produto.garantia}}
    </div>
    <div>
      <label>Imagem:</label>
      {{this.produto.imagem}}
    </div>
    <div>
      <label>Preco_Compra:</label>
      {{this.produto.preco_compra}}
    </div>
    <div>
      <label>Preco_Venda:</label>
      {{this.produto.preco_venda}}
    </div>
  </div>
  <div v-else>
    <br />
    <p>Clique em um produto...</p>
  </div>
</template>
<script>
import http from "../http-common";
export default {
  name: "produto",
  props: ["produto"],
  methods: {
    deleteProduto() {
      http
        .delete("/produto/" + this.produto.id)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          this.$router.push("/");
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>