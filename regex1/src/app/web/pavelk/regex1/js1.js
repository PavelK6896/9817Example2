
const s = '12ss34tt56 erwterter44444444 45534534534'
const s2 = s.replace(/\D/g, '')
const s3 = s.match(/(?:^|\D)(\d{5})(?!\d)/g)
const s4 = s.match(/\b\d{5}\b/g);

console.log(s2)
console.log(s3)
console.log(s4)

const s5 = s2.slice(0, 5)
console.log(s5.match(/\b\d{5}\b/g))
const s6 = s2.slice(0, 3)
console.log(s6.match(/\b\d{0,5}\b/g))

