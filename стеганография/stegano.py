from stegano import lsb

secret =lsb.hide("1018.png", "secret")
secret.save("secretMessage.png")

text=lsb.reveal("secretMessage.png")
print(text)