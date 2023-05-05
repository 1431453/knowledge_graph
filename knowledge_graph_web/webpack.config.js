const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
module.exports = {
    // 其他配置项
    plugins: [
        new BundleAnalyzerPlugin({
            // 其他配置
            analyzerMode: 'server', // 将 analyzerMode 设置为 'server'
            openAnalyzer: true, // 是否自动打开分析报告，默认为 false
            type: 'server' // 将 type 设置为 'server'
        })
    ],
    resolve: {
        extensions: ['.js', '.jsx'],  // 添加这行
    },

};