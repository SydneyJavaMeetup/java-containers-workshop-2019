import { check } from "k6";
import http from "k6/http";

let host = "alb-SydneyJavaContainers-873543138.ap-southeast-2.elb.amazonaws.com" //"localhost:8080"

export default function() {
  http.post("http://" + host + "/", JSON.stringify({id: "4321", url: "http://k6.test", popped: true}), { headers: {"Content-Type": "application/json"} })

  let res = http.get("http://" + host + "/4321/");
  check(res, {
          "is status 200": (r) => r.status === 200,
          "returned correct balloon id": (r) => r.json("id") === "4321",
          "returned correct balloon url": (r) => r.json("url") === "http://k6.test",
          "returned correct balloon popped": (r) => r.json("popped") === true
  });

};