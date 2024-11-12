/** @type {import('next').NextConfig} */
const ContentSecurityPolicy = require('./csp')
const redirects = require('./redirects')

const nextConfig = {
  typescript: {
    ignoreBuildErrors: true,
  },
  reactStrictMode: true,
  swcMinify: true,
  images: {
    domains: ['localhost', process.env.NEXT_PUBLIC_SERVER_URL]
      .filter(Boolean)
      .map(url => url.replace(/https?:\/\//, '')),
  },
  redirects,
  async headers() {
    const headers = []

    // Prevent search engines from indexing the site if it is not live
    if (!process.env.NEXT_PUBLIC_IS_LIVE) {
      headers.push({
        headers: [
          {
            key: 'X-Robots-Tag',
            value: 'noindex',
          },
        ],
        source: '/:path*',
      })
    }

    // Set the CSP header properly, making sure no extra line breaks exist
    headers.push({
      source: '/(.*)',
      headers: [
        {
          key: 'Content-Security-Policy',
          value: `
            default-src 'self';
            style-src 'self' 'unsafe-inline' https://fonts.googleapis.com https://vercel.live;
            connect-src 'self' https://checkout.stripe.com https://api.stripe.com https://maps.googleapis.com wss://ws-us3.pusher.com;
            font-src 'self' https://fonts.googleapis.com https://vercel.live;
            img-src 'self' data: https://www.gravatar.com https://your-image-domain.com;
            script-src 'self' 'unsafe-inline' https://www.gstatic.com;
            object-src 'none';
          `.replace(/\n/g, ' ').trim(), // Remove any unwanted newline characters
        },
      ],
    })

    return headers
  },
}

module.exports = nextConfig
