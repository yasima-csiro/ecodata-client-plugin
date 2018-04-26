<g:if test="${!include || include.contains('multi-input')}">
    <script id="template-multi-input" type="text/html">
    <div data-bind="foreach: observableValues">
        <div class="input-append">
            <span data-bind="template:{nodes:$componentTemplateNodes}"></span><span class="add-on"
                                                                                    data-bind="click:$parent.removeValue"><i
                    class="fa fa-remove"></i></span>
        </div>
    </div>
    <i class="fa fa-plus" data-bind="click:addValue"></i>

    </script>
</g:if>

<g:if test="${!include || include.contains('condition-trajectory')}">
    <script type="text/html" id="template-condition-trajectory">
    <div class="row-fluid">
        <div class="span3" style="text-align: center">Very poor</div>

        <div class="span3">Poor</div>

        <div class="span3">Good</div>

        <div class="span3">Very good</div>
    </div>
    <svg viewBox="0 0 304 40" class="condition-trajectory" aria-labeledby="title" role="diagram">
        <svg x="0" y="3">
            <g>
                <rect class="condition condition-very-poor" x="0" width="75" height="26" y="0"></rect>
                <rect class="condition condition-poor" x="75" width="75" height="26" y="0"></rect>
                <rect class="condition condition-good" x="150" width="75" height="26" y="0"></rect>
                <rect class="condition condition-very-good" x="225" width="75" height="26" y="0"></rect>
            </g>
        </svg>
        <svg data-bind="attr:{x:boxPosition, title:title}" y="0">
            <g transform="scale(0.3)">
                <rect class="trajectory" x="4" y="5" width="95" height="93"></rect>
                <svg class="trajectory-icon" x="10" y="10" width="80" height="80"
                     data-bind="template:trajectoryTemplate">

                </svg>
            </g>
        </svg>
    </svg>

    </script>

    <script id="template-trajectory-improving" type="text/html">
    <svg viewBox="0 0 16 16">
        <path fill="#000000"
              d="M3.707 13.707l8.293-8.293v3.586c0 0.552 0.448 1 1 1s1-0.448 1-1v-6c0-0.404-0.244-0.769-0.617-0.924-0.124-0.051-0.254-0.076-0.383-0.076v-0.001h-6c-0.552 0-1 0.448-1 1s0.448 1 1 1h3.586l-8.293 8.293c-0.195 0.195-0.293 0.451-0.293 0.707s0.098 0.512 0.293 0.707c0.39 0.391 1.024 0.391 1.414 0z"></path>
    </svg>
    </script>

    <script id="template-trajectory-deteriorating" type="text/html">
    <svg viewBox="0 0 16 16">
        <path d="M2.293 3.707l8.293 8.293h-3.586c-0.552 0-1 0.448-1 1s0.448 1 1 1h6c0.404 0 0.769-0.244 0.924-0.617 0.051-0.124 0.076-0.254 0.076-0.383h0.001v-6c0-0.552-0.448-1-1-1s-1 0.448-1 1v3.586l-8.293-8.293c-0.195-0.195-0.451-0.293-0.707-0.293s-0.512 0.098-0.707 0.293c-0.391 0.39-0.391 1.024 0 1.414z"></path>
    </svg>
    </script>
    <script id="template-trajectory-stable" type="text/html">
    <svg viewBox="0 0 100 100">
        <path d="M12 43 h80 v14 h-80 z"></path>
    </svg>

    </script>
    <script id="template-trajectory-unclear" type="text/html">
    <svg viewBox="0 0 1792 1792">
        <path d="M1088 1256v240q0 16-12 28t-28 12h-240q-16 0-28-12t-12-28v-240q0-16 12-28t28-12h240q16 0 28 12t12 28zm316-600q0 54-15.5 101t-35 76.5-55 59.5-57.5 43.5-61 35.5q-41 23-68.5 65t-27.5 67q0 17-12 32.5t-28 15.5h-240q-15 0-25.5-18.5t-10.5-37.5v-45q0-83 65-156.5t143-108.5q59-27 84-56t25-76q0-42-46.5-74t-107.5-32q-65 0-108 29-35 25-107 115-13 16-31 16-12 0-25-8l-164-125q-13-10-15.5-25t5.5-28q160-266 464-266 80 0 161 31t146 83 106 127.5 41 158.5z"/>
    </svg>
    </script>
    <script id="template-trajectory-none" type="text/html">

    </script>
</g:if>