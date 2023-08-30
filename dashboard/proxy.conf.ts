const proxy = [
  {
    context: '/api',
    target: 'http://localhost:8100',
    pathRewrite: { '^/api': '' },
    secure: false,
    changeOrigin: true,
    logLevel: 'debug',
  },
];
module.exports = proxy;
