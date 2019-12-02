import Vue from "vue";
import Router from "vue-router";
import ListaProdutos from "./components/ListaProdutos.vue";
import AddProduto from "./components/AddProduto.vue";
import PesquisarProduto from "./components/PesquisarProduto.vue";
import Produto from "./components/Produto.vue";
Vue.use(Router);
export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            name: "produtos",
            alias: "/produto",
            component: ListaProdutos,
            children: [
                {
                    path: "/produto/:id",
                    name: "produto-detalhes",
                    component: Produto,
                    props: true
                }
            ]
        },
        {
            path: "/add",
            name: "add",
            component: AddProduto
        },
        {
            path: "/pesquisar",
            name: "pesquisar",
            component: PesquisarProduto
        }
    ]
});