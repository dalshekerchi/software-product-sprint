// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Gets number of github contributions from my account
 */

async function getContributions() {
    var data = await (await fetch(`https://corsanywhere.herokuapp.com/https://github-contributions-api.deno.dev/dalshekerchi.json`)).json();
    const totalContributions = data.totalContributions;

    const dateContainer = document.getElementById('contributions');
    dateContainer.innerText = totalContributions;
}

async function randomFreeTimeActivity() {
    const getActivities = await fetch('/free-time');
    const activity = await getActivities.json();
  
    // Pick a random greeting.
    const chooseRandomActivity = activity[Math.floor(Math.random() * activity.length)];
  
    // Add it to the page.
    const greetingContainer = document.getElementById('free-time');
    greetingContainer.innerText = chooseRandomActivity;
  }