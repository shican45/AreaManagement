import Vue from 'vue'
import Router from 'vue-router'
import Area from '@/components/Area'
import City from '@/components/City'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/area'
    },
    {
      path: '/area',
      component: Area,
      meta: { title: '域区管理' }
    },
    {
      path: '/city',
      component: City,
      meta: { title: '城市信息' }
    },
    {
      path: '*',
      redirect: '/'
    }
  ]
})
