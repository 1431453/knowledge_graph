import { createRouter, createWebHistory } from 'vue-router';
// import Home from '../components/myIndex'
import EmptyPage from '../views/emptyPage'
import qaSearch from '../views/qaSearch'
import threeSearchContent from '../views/threeSearchContent'
import allEntity from "@/views/allEntity";
import entityTree from "@/views/entityTree";
import graphShow from "@/views/graphShow";
import myTest from "@/views/myTest";
const routes = [
    {
        path:'/',
        redirect:'/three-search-content'
    },
    {
        path:'/qa-search',
        component: qaSearch,

    },
    {
        path:'/three-search-content',
        component: threeSearchContent,
    },
    {
        path:'/empty-page',
        component: EmptyPage,
    },
    {
        path: '/all-entity',
        component: allEntity,
    },
    {
        path:'/entity-tree',
        component: entityTree
    },
    {
        path: '/graph-show',
        component: graphShow
    },
    {
        path: '/test',
        component: myTest
    }
]
const router = createRouter({
    history: createWebHistory(),
    routes,
  });
export default router;


