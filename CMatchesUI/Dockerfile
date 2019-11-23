FROM node:10.9
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
#ENV PATH /usr/src/app/node_modules/.bin:$PATH
COPY package.json /usr/src/app
RUN npm install
RUN npm install -g @angular/cli
COPY . /usr/src/app
EXPOSE 4200
CMD [ "ng", "serve" ]
