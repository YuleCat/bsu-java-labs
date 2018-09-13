from PIL import Image, ImageDraw


s = input()

img = Image.open(s)

img = img.convert('RGBA')

pix = img.load()

for x in range(img.size[0]):
    
	for y in range(img.size[1]):
        
		if sum(pix[(x, y)][:3]) == 765:
            
			q = list(pix[(x, y)])
            
			q[3] = 0
            
			pix[(x, y)] = tuple(q)


img.save(s)
