const isDevelopment = process.env.NODE_ENV === 'development';

const policies = {
  'default-src': ["'self'"],
  'script-src': [
    "'self'",
    "'unsafe-inline'",
    isDevelopment ? "'unsafe-eval'" : "", 
    'https://checkout.stripe.com',
    'https://js.stripe.com',
    'https://maps.googleapis.com',
    'https://vercel.live',
  ].filter(Boolean),
  'child-src': ["'self'"],
  'style-src': ["'self'", "'unsafe-inline'", 'https://fonts.googleapis.com'],
  'img-src': ["'self'", 'https://*.stripe.com', 'https://raw.githubusercontent.com'],
  'font-src': ["'self'", 'https://fonts.gstatic.com'], 
  'frame-src': [
    "'self'",
    'https://checkout.stripe.com',
    'https://js.stripe.com',
    'https://hooks.stripe.com',
    'https://vercel.live',
  ],
  'connect-src': [
    "'self'",
    'https://checkout.stripe.com',
    'https://api.stripe.com',
    'https://maps.googleapis.com',
  ],
};

module.exports = {
  images: {
    domains: ['localhost'], // Ensure 'localhost' is added here
    remotePatterns: [
      {
        protocol: 'http',
        hostname: 'localhost',
        port: '3000',
        pathname: '/media/**', // Adjust this if necessary
      },
    ],
  },
  async headers() {
    return [
      {
        source: '/(.*)',
        headers: [
          {
            key: 'Content-Security-Policy',
            value: Object.entries(policies)
              .map(([key, value]) => `${key} ${value.join(' ').trim()}`)
              .join('; '),
          },
        ],
      },
    ];
  },
};
