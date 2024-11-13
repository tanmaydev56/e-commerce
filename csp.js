const policies = {
  'default-src': ["'self'"],
  'script-src': [
    "'self'",
    "'unsafe-inline'",
    "'unsafe-eval'",  // Used in development for React Refresh
    'https://checkout.stripe.com',
    'https://js.stripe.com',
    'https://maps.googleapis.com',
    'https://vercel.live',
    'https://www.gstatic.com',  // Added for Google scripts
  ],
  'child-src': ["'self'"],
  'style-src': [
    "'self'",
    "'unsafe-inline'", // Inline styles
    'https://fonts.googleapis.com', // External fonts
    'https://vercel.live', // Added Vercel for live deployments
  ],
  'img-src': [
    "'self'",
    'https://*.stripe.com',
    'https://raw.githubusercontent.com',
    'https://www.gravatar.com', // Added Gravatar for avatar images
  ],
  'font-src': [
    "'self'",
    'https://fonts.googleapis.com', // External fonts
  ],
  'frame-src': [
    "'self'",
    'https://checkout.stripe.com',
    'https://js.stripe.com',
    'https://hooks.stripe.com',
    'https://vercel.live', // Added Vercel
  ],
  'connect-src': [
    "'self'",
    'https://checkout.stripe.com',
    'https://api.stripe.com',
    'https://maps.googleapis.com',
    'wss://ws-us3.pusher.com', // Added Pusher for WebSocket connection
  ],
  'object-src': ["'none'"],  // Block plugin content like Flash or Java applets
  'base-uri': ["'self'"], // Restrict where <base> tags can point
  'form-action': ["'self'"], // Only allow forms to be submitted to the same origin
}

module.exports = Object.entries(policies)
  .map(([key, value]) => {
    return `${key} ${value.join(' ')}`
  })
  .join('; ')
