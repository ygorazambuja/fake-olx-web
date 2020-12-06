FROM node:14-alpine
COPY . /usr/src/webapp
WORKDIR /usr/src/webapp
RUN npm install
EXPOSE 8080
CMD [ "npm", "run", "serve" ]
