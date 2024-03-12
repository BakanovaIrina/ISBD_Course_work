/** @type {import('tailwindcss').Config} */
module.exports = {
	mode: 'jit',
	content: [
		"./src/**/*.{html,ts}"
	],
	theme: {
		screens: {
			'tablet': '753px',
			'desktop': '1232px'
		},
	},
	plugins: [],
}
