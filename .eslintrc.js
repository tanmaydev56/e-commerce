module.exports = {
  root: true,
  extends: ['plugin:@next/next/recommended', '@payloadcms'],
  ignorePatterns: ['**/payload-types.ts'],
  plugins: ['prettier'],
  rules: {
    // Disable all rules
    'prettier/prettier': 'off',
    'no-console': 'off',
    'simple-import-sort/imports': 'off',
    'react-hooks/exhaustive-deps': 'off',
    '@next/next/no-img-element': 'off',
    '@next/next/no-page-custom-font': 'off',
    '@next/next/no-script-component': 'off',
    // Add more rules here as needed
  },
}
