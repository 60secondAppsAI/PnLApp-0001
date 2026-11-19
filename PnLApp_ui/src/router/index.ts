import { createRouter, createWebHashHistory } from 'vue-router';
import { useAuth } from '../stores/auth';
import SignIn from '../components/SignIn.vue';
import Settings from '../components/Settings.vue';
import MyDashboard from '../components/MyDashboard.vue';

// Dynamic imports for module components
import PnLRecords from '../components/PnLRecords.vue';
import PnLRecordDetail from '../components/PnLRecordDetail.vue';
import InstrumentPnLs from '../components/InstrumentPnLs.vue';
import InstrumentPnLDetail from '../components/InstrumentPnLDetail.vue';
import StrategyPnLs from '../components/StrategyPnLs.vue';
import StrategyPnLDetail from '../components/StrategyPnLDetail.vue';
import PnLAdjustments from '../components/PnLAdjustments.vue';
import PnLAdjustmentDetail from '../components/PnLAdjustmentDetail.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: MyDashboard
  },
  
  // Dynamic module routes
  {
    path: '/pnLRecords',
    name: 'PnLRecords',
    component: PnLRecords
  },
  {
    path: '/pnLRecord/:id',
    name: 'PnLRecordDetail',
    component: PnLRecordDetail,
    props: true
  },
  {
    path: '/instrumentPnLs',
    name: 'InstrumentPnLs',
    component: InstrumentPnLs
  },
  {
    path: '/instrumentPnL/:id',
    name: 'InstrumentPnLDetail',
    component: InstrumentPnLDetail,
    props: true
  },
  {
    path: '/strategyPnLs',
    name: 'StrategyPnLs',
    component: StrategyPnLs
  },
  {
    path: '/strategyPnL/:id',
    name: 'StrategyPnLDetail',
    component: StrategyPnLDetail,
    props: true
  },
  {
    path: '/pnLAdjustments',
    name: 'PnLAdjustments',
    component: PnLAdjustments
  },
  {
    path: '/pnLAdjustment/:id',
    name: 'PnLAdjustmentDetail',
    component: PnLAdjustmentDetail,
    props: true
  },

  // Core application routes
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
//  {
//    path: '/users',
//    name: 'Users',
//    component: Users,
//    meta: { requiresAuth: true }
//  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    meta: { requiresAuth: true }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0 };
  }
});

// Navigation guard for authentication
router.beforeEach((to, from, next) => {
  const auth = useAuth();
  
  if (to.meta.requiresAuth && !auth.isAuthenticated) {
    next({ name: 'SignIn', query: { redirect: to.fullPath } });
  } else if (to.name === 'SignIn' && auth.isAuthenticated) {
    next({ name: 'Dashboard' });
  } else {
    next();
  }
});

export default router;
